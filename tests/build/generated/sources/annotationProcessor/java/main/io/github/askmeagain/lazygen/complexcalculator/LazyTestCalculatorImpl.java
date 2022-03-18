package io.github.askmeagain.lazygen.complexcalculator;

import io.github.askmeagain.lazygen.output.ComplexOutputs;
import io.github.askmeagain.lazygen.output.ComplexOutputs.ComplexOutputsBuilder;
import io.github.askmeagain.lazygen.output.Multiplications;
import io.github.askmeagain.lazygen.output.Multiplications.MultiplicationsBuilder;
import io.github.askmeagain.lazygen.output.Summations;
import io.github.askmeagain.lazygen.output.Summations.SummationsBuilder;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-18T23:04:08+0100",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 17.0.1 (SAP SE)"
)
public class LazyTestCalculatorImpl extends LazyTestCalculator {

    @Override
    public Summations mapSummations(TestCalculator calculator) {
        if ( calculator == null ) {
            return null;
        }

        SummationsBuilder summations = Summations.builder();

        summations.a( a( calculator ) );
        summations.aa( aa( calculator ) );
        summations.aaaa( aaaa( calculator ) );

        return summations.build();
    }

    @Override
    public Multiplications mapMultiplications(TestCalculator calculator) {
        if ( calculator == null ) {
            return null;
        }

        MultiplicationsBuilder multiplications = Multiplications.builder();

        multiplications.number1mul2( number1mul2( calculator ) );
        multiplications.number1mul4( number1mul4( calculator ) );
        multiplications.number1mul8( number1mul8( calculator ) );

        return multiplications.build();
    }

    @Override
    public ComplexOutputs map(TestCalculator calculator) {
        if ( calculator == null ) {
            return null;
        }

        ComplexOutputsBuilder complexOutputs = ComplexOutputs.builder();

        complexOutputs.multiplications( mapMultiplications(calculator) );
        complexOutputs.summations( mapSummations(calculator) );

        return complexOutputs.build();
    }
}
