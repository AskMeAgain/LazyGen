package io.github.askmeagain.lazygen;

import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-18T22:50:46+0100",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 17.0.1 (SAP SE)"
)
public class LazyMapstructAbstractClassImpl extends LazyMapstructAbstractClass {

    @Override
    String map(String input) {
        if ( input == null ) {
            return null;
        }

        String string = a();

        return string;
    }
}
