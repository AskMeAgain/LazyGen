# LazyGen

This annotation processor adds a lazy/cacheable method to any non-final method annotated with `@LazyGen` via code
generation.

Note: A Lazy/Cacheable method is a method which only executes its body once and keeps a copy of the result in memory.

## Gradle

    annotationProcessor 'io.github.askmeagain:lazygenprocessor:1.2.0'
    implementation 'io.github.askmeagain:lazygenprocessor:1.2.0'

## Getting Started

1. Add LazyGen package and annotation processor to your project
2. Add `@GenerateLazyClass(ResultType.XXX)` to a class (or interface in case
   of [MapStruct mapper](https://github.com/mapstruct/mapstruct))
3. Specify how your result should look like:
   1. `ResultType.CLASS`: Creates a normal class which extends the original class
   2. `ResultType.ABSTRACT_CLASS`: Creates an abstract class which extends/implements the interface
   3. `ResultType.MAPSTRUCT_COMPATIBLE`: Creates an abstract class, prepared correctly for usage with
      [MapStruct](https://github.com/mapstruct/mapstruct)
   4. `ResultType.MAPSTRUCT_COMPATIBLE_WITHOUT_ANNOTATIN` is the same as step 3, but the @Mapper annotation is not added
4. Add `@LazyGen` to any method on this class and any childs (class or interface doesnt matter)
5. Hit build
6. A class is generated which inherits the original class, with Lazy as suffix

## OneTimeUsage vs MultiUsage

You can also specify how the caching should be implemented:

* `ONE_TIME_USE`: returns always the same (cached) value, ignoring the input parameters
* `MULTI_USE`: will cache the result of the method in a map based on a key. The key is calculated via
  `hashCode()` of the input parameters
* `PARENT`: default on `@LazyGen` methods, use what the `@GenerateLazyClass` specified (by default `ONE_TIME_USE`)

You can configure a general usage, via `@GenerateLazyClass(usage = ONE_TIME_USE)`, but each `@LazyGen` method can
override this behaviour by themselves.

## Making MapStruct lazy

1. Add `@GenerateLazyClass(ResultType.MAPSTRUCT_COMPATIBLE)` to your mapstruct mapper
2. Remove `@Mapper` annotation from your mapper
3. Add `@LazyGen` to any method
4. Get your MapStruct mapper via `Mappers.getMapper(XXXXXXLazy.class);`

Note: Methods which are touched by the MapStruct annotation processor can only be made lazy if they are
implemented/referenced via `@Named` annotation or else MapStruct cannot find the correct method since the `@Named`
annotation is not inheritable.

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
