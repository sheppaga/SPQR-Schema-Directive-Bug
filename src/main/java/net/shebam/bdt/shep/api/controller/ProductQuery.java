package net.shebam.bdt.shep.api.controller;

import org.springframework.stereotype.Component;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import net.shebam.bdt.shep.api.domain.Product;

import java.util.HashSet;
import java.util.Set;

@Component
@GraphQLApi
public class ProductQuery {

    @GraphQLQuery(name = "getProductsInStock")
    public Set<Product> getProductsInStock(){
        Set<Product> mockResult = new HashSet<>();
        Product product1 = new Product("1","Table", 899);
        mockResult.add(product1);
        Product product2 = new Product("2","Couch", 500);
        mockResult.add(product2);
        return mockResult;
    }


}