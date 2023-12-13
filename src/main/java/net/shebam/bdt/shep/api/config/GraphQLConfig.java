package net.shebam.bdt.shep.api.config;

import org.springframework.context.annotation.Configuration;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.SchemaPrinter;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

import org.springframework.context.annotation.Bean;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@Configuration
public class GraphQLConfig {

    @Autowired
    private ApplicationContext applicationContext;
    
    @Bean
    public GraphQLSchema graphQLSchema() {
        //Note, the default resolver builder doesn't find a @GraphQLAPI annotated type despite its existence, hence the need for this
        Map<String, Object> graphQLApiBeans = applicationContext.getBeansWithAnnotation(GraphQLApi.class);
        GraphQLSchemaGenerator schemaGenerator = new GraphQLSchemaGenerator();
        graphQLApiBeans.values().forEach(bean -> {
            schemaGenerator.withOperationsFromSingleton(bean);
        });
        GraphQLSchema mySchema = schemaGenerator.generate();
        return mySchema;
    }

    @Bean
    public GraphQL graphQL() {
        GraphQL gql = GraphQL.newGraphQL(graphQLSchema())
        .build();

        SchemaPrinter schemaPrinter = new SchemaPrinter();
        String printedSchema = schemaPrinter.print(gql.getGraphQLSchema()); //causes the error
        System.out.println(printedSchema);

        return gql;
    }

}
