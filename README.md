# LazyGen

This annotation processor is designed to add LazyMethods to any method via annotations.

## Getting Started

1. Add annotation processor to your project
2. Add @GenerateLazyClass to a class which should have lazy methods
3. Add @LazyGen to any method on this class (and interfaces)
4. Hit build
5. A class is generated which inherits the original class, prefixed with Lazy

## Making MapStruct lazy

Note: The code gen relies on the @Named annotation. You can only make @Named methods lazy

1. Add @GenerateLazyClass(mapstruct = true) to your mapstruct mapper 
2. Add @LazyGen to any @Named method
3. Get your mapStruct mapper via Mappers.getMapper(LazyXXXXXX.class);

## LazyGenInput\<T> 

LazyGen will search for a method called "map" and an interface LazyGenInput 
and will add a calculation method, which maps from the LazyGenInput generic type
to the map output type. If all your mapping methods have the calculator as type,
it allows you to create a composable "Calculator", which is super efficient.

Checkout the examples (TestCalculator)