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

### LazyGenInput\<T> 

LazyGen will search for a method called "map" and an interface LazyGenInput on the class/interface marked
with @GenerateLazyClass
and will add a calculation method, which maps from the LazyGenInput generic type
to the map output type. If all your mapping methods have the calculator as type,
it allows you to create a composable "Calculator", which is super efficient and allows you
to reference your own methods in a lazy way 

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

    @GenerateLazyClass(mapstruct = true)
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
        public java.lang.String a(io.github.askmeagain.lazygen.calculator.TestMapper _TestMapper0) {
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