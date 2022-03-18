package io.github.askmeagain.lazygen;

import io.github.askmeagain.lazygen.output.Outputs;
import io.github.askmeagain.lazygen.output.Outputs.OutputsBuilder;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-18T23:04:08+0100",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 17.0.1 (SAP SE)"
)
public class LazyMapStructInterfaceImpl extends LazyMapStructInterface {

    @Override
    public Outputs map(String input) {
        if ( input == null ) {
            return null;
        }

        OutputsBuilder outputs = Outputs.builder();

        outputs.output( a( input ) );

        return outputs.build();
    }
}
