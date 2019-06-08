package com.example.demo.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.modal.Brand;
import com.example.demo.modal.ProductModel;
import com.example.demo.modal.Response;
import com.example.demo.repository.BrandRepositiry;
import com.example.demo.repository.ProductModelRepository;
import com.example.demo.vo.AllProductVO;
import com.example.demo.vo.ModelVO;

@Service
public class ModelService {

    @Autowired
    private ProductModelRepository modelRepository;

    @Autowired
    private BrandRepositiry brandRepositiry;

    public Map<String, List<AllProductVO>> getAllAvailableProduct() {

        List<Brand> brandResult = brandRepositiry.findAll();
        Map<String, List<ProductModel>> result = brandResult.stream()
                .collect(Collectors.toMap(b -> b.getName() + "-" + b.getCategory(),
                        b -> modelRepository.findByBrand_IdAndDeprecated(b.getId(), false)));

        Map<String, List<AllProductVO>> optimisedResult = result.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> e.getValue().stream().map(AllProductVO::new)
                                .collect(Collectors.toList())));

        LinkedHashMap<String, List<AllProductVO>> sortedResult = new LinkedHashMap<>();
        optimisedResult.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> sortedResult.put(x.getKey(), x.getValue()));

        return sortedResult;
    }

    public ResponseEntity addNewModel(ModelVO modelVO) {
        ProductModel m = new ProductModel();
        Brand b = brandRepositiry
                .findByNameAndCategory(modelVO.getBrandName(), modelVO.getCategoryName());
        m.setBrand(b);
        m.setDeprecated(false);
        m.setName(modelVO.getModelName());

        if (modelRepository.findByName(modelVO.getModelName()) != null) {
            return new ResponseEntity<>(new Response("Duplicate model!", 500), HttpStatus.BAD_REQUEST);
        } else {
            modelRepository.save(m);
            return new ResponseEntity<>(new Response("Model saved", 200), HttpStatus.OK);
        }
    }

    public ResponseEntity deprecateModel(ModelVO modelVO) {
        ProductModel m = modelRepository.findByName(modelVO.getModelName());
        if (modelVO.getBrandName().equals(m.getBrand().getName())) {
            m.setDeprecated(true);
            modelRepository.save(m);
        }
        return new ResponseEntity<>(new Response("Model deleted", 200), HttpStatus.OK);
    }
}
