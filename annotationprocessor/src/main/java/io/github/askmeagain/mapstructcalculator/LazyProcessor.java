package io.github.askmeagain.mapstructcalculator;

import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.PrintWriter;
import java.util.Set;

@SupportedAnnotationTypes("io.github.askmeagain.mapstructcalculator.GenerateProfiler")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class LazyProcessor extends AbstractProcessor {

  @lombok.SneakyThrows
  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    if(annotations.isEmpty()){
      return true;
    }

    annotations.forEach(x -> {
      System.out.println(x.toString());
    });

    JavaFileObject builderFile = processingEnv.getFiler()
        .createSourceFile("io.github.askmeagain.mapstructcalculator.PseudoGeneratedCalculator");

    try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {
      out.println("""
          package io.github.askmeagain.mapstructcalculator;
            
            import org.mapstruct.Mapper;
            import org.mapstruct.Named;
            
            @Mapper
            public abstract class PseudoGeneratedCalculator extends Calculator {
            
              private String calculateStuff;
            
              @Override
              @Named("calculateStuff")
              String calculateStuff(Inputs inputs){
                if(calculateStuff != null){
                  return calculateStuff;
                }
                calculateStuff = super.calculateStuff(inputs);
                return calculateStuff;
              }
            }
          """);
    }

    return true;
  }
}
