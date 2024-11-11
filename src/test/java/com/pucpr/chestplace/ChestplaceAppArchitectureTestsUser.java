package com.pucpr.chestplace;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@SpringBootTest
@AnalyzeClasses(packages = {"com.pucpr.chestplace.features"})
public class ChestplaceAppArchitectureTestsUser {
    
    @ArchTest
    static final ArchRule controllers_should_not_access_repositories_directly = 
        ArchRuleDefinition.noClasses()
            .that().resideInAPackage("..controllers..")
            .should().accessClassesThat().resideInAPackage("..repositories..");

    @ArchTest
    static final ArchRule controllers_should_not_access_entities_directly = 
        ArchRuleDefinition.noClasses()
            .that().resideInAPackage("..controllers..")
            .should().accessClassesThat().resideInAPackage("..entities..");

    @ArchTest
    static final ArchRule repositories_should_not_access_controllers =
        ArchRuleDefinition.noClasses()
            .that().resideInAPackage("..repositories..")
            .should().accessClassesThat().resideInAPackage("..controllers..");

    @ArchTest
    static final ArchRule controllers_name_should_end_with_controller =
        ArchRuleDefinition.classes()
                .that().areAnnotatedWith(RestController.class)
                .should().haveSimpleNameEndingWith("Controller");
    @ArchTest
    static final ArchRule services_name_should_end_with_service =
        ArchRuleDefinition.classes()
                .that().areAnnotatedWith(Service.class)
                .should().haveSimpleNameEndingWith("Service");
    @ArchTest
    static final ArchRule repositories_name_should_end_with_repository =
        ArchRuleDefinition.classes()
                .that().areAssignableTo(JpaRepository.class)
                .should().haveSimpleNameEndingWith("Repository");

//    @ArchTest
//    static final ArchRule layered_architecture = Architectures.layeredArchitecture()
//        .consideringOnlyDependenciesInLayers()
//        .layer("Controllers").definedBy("..controllers..")
//        .layer("Services").definedBy("..services..")
//        .layer("Repositories").definedBy("..repositories..")
//        .whereLayer("Controllers").mayNotBeAccessedByAnyLayer()
//        .whereLayer("Repositories").mayOnlyBeAccessedByLayers("Services");
    
}
