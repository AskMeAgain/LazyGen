# LazyGen

This annotation processor is designed to add LazyMethods to any method via annotations.

## Getting Started

1. Add annotation processor to your project
2. Add @LazyGen to any method.
3. Hit build
4. A class is generated which inherits the original class, prefixed with Lazy

## Making MapStruct lazy

Note: The code gen relies on the @Named annotation. You can only make @Named methods lazy

1. Add @GenerateLazyMapStructMapper to your mapstruct mapper
2. Add @LazyGen to any @Named method
3. Get your mapStruct mapper via Mappers.getMapper(LazyXXXXXX.class);