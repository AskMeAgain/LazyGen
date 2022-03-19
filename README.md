# LazyGen

This annotation processor adds a lazy¹ method to any non-final method annotated with `@LazyGen` via code generation.

¹ Lazy/Cacheable is a method which only executes its body once and keeps a copy of the result in memory

## Maven Dependency

    <dependency>
        <groupId>io.github.askmeagain</groupId>
        <artifactId>lazygenprocessor</artifactId>
        <version>0.0.2</version>
        <type>module</type>
    </dependency>

## Getting Started

1. Add LazyGen package and annotation processor to your project
2. Add `@GenerateLazyClass(ResultType.XXX)` to a class (or interface in case
   of [MapStruct mapper](https://github.com/mapstruct/mapstruct))
3. Specify how your result should look like:
    1. `ResultType.CLASS`: Creates a normal class which extends the original class
    2. `ResultType.ABSTRACT_CLASS`: Creates an abstract class which extends/implements the interface
    3. `ResultType.MAPSTRUCT_COMPATIBLE`: Creates an abstract class, prepared correctly for usage with
       [MapStruct](https://github.com/mapstruct/mapstruct)
4. Add `@LazyGen` to any method on this class and any childs (class or interface doesnt matter)
5. Hit build
6. A class is generated which inherits the original class, prefixed with Lazy

## Making MapStruct lazy

Note: The code gen relies on the `@Named` annotation. You can only make `@Named` methods lazy

1. Add `@GenerateLazyClass(ResultType.MAPSTRUCT_COMPATIBLE)` to your mapstruct mapper
2. Add `@LazyGen` to any `@Named` method
3. Get your MapStruct mapper via `Mappers.getMapper(LazyXXXXXX.class);`

## Examples

### Simple Example

<details><summary>Before</summary>
<p>

    @GenerateLazyClass
    public class NormalClass {
        
        @LazyGen
        String abc(){
            return "Test";
        }
    }

</p>
</details>

<details><summary>After</summary>
<p>

    public class LazyNormalClass extends NormalClass {
        private java.lang.String _abc;
        
        @Override
        public java.lang.String abc() {
            if (_abc != null) {
                return _abc;
            }
            _abc = super.abc();
            return _abc;
        }
    }

</p>
</details>

### MapStruct Example

<details><summary>Before</summary>
<p>

    @GenerateLazyClass(ResultType.MAPSTRUCT_COMPATIBLE)
    public interface TestMapper {
    
        @Mapping(target = ".", source = "input", qualifiedByName = "a")
        String mapSummations(String input);
        
        @LazyGen
        @Named("a")
        default String a(TestMapper calculator) {
            System.out.println("a");
            return "a";
        }
    }

</p>
</details>

<details><summary>After</summary>
<p>

    @Mapper
    public abstract class LazyTestMapper implements TestMapper {
    
        @Named("a")
        @Override
        public java.lang.String a(io.github.askmeagain.lazygen.calculator.simple.MapstructAbstractClass _TestMapper0) {
            if (_a != null) {
                return _a;
            }
            _a = TestMapper.super.a(_TestMapper0);
            return _a;
        }
        private java.lang.String _a;
    }

</p>
</details>