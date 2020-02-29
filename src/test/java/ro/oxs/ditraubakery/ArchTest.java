package ro.oxs.ditraubakery;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("ro.oxs.ditraubakery");

        noClasses()
            .that()
                .resideInAnyPackage("ro.oxs.ditraubakery.service..")
            .or()
                .resideInAnyPackage("ro.oxs.ditraubakery.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..ro.oxs.ditraubakery.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
