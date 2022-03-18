# LazyGen

This annotation processor is designed to add LazyMethods to any method marked with an annotation
by generating a new Class which inherites the original behaviour.

## Getting Started

1. Add LazyGen annotation processor to your project
2. Add @GenerateLazyClass(ResultType.XXX) to a class which should have lazy methods
3. Specify how your result should look like:
   1. **ResultType.CLASS**: Creates a normal class which extends the original class
   2. **ResultType.ABSTRACT_CLASS**: creates an abstract class which extends/implements the interface
   3. **ResultType.MAPSTRUCT_COMPATIBLE**: Creates an abstract class, prepared correctly for usage with MapStruct
4. Add @LazyGen to any method on this class and (sub)(sub)interfaces
5. Hit build
6. A class is generated which inherits the original class, prefixed with Lazy

## Making MapStruct lazy

Note: The code gen relies on the @Named annotation. You can only make @Named methods lazy

1. Add `@GenerateLazyClass(ResultType.MAPSTRUCT_COMPATIBLE)` to your mapstruct mapper 
2. Add `@LazyGen` to any `@Named` method
3. Get your MapStruct mapper via Mappers.getMapper(LazyXXXXXX.class);

### LazyGenInput\<T> 

The LazyGen annotation processor will search for a method called "map" and an interface LazyGenInput on the class/interface marked
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
        public java.lang.String a(io.github.askmeagain.lazygen.MapstructAbstractClass _TestMapper0) {
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

### LazyGenInput\<T>

<details><summary>Before</summary>
<p>

    @GenerateLazyClass(ResultType.CLASS)
    public class NormalClassWithInput implements LazyGenInput<Input> {
    
        @LazyGen
        String map(NormalClassWithInput instance){
            return "Test";
        }
    }

</p>
</details>

<details><summary>After</summary>
<p>

    public class  LazyNormalClassWithInput extends NormalClassWithInput {
        private Input inputs;
        
        @Override
        public Input getInputs(){
            return inputs;
        }
        
        public String calculate(Input inputs){
            this.inputs = inputs;
            return map(this);
        }
    
        @Override
        public java.lang.String map(io.github.askmeagain.lazygen.NormalClassWithInput _NormalClassWithInput0) {
            if (_map != null) {
                return _map;
            }
            _map = super.map(_NormalClassWithInput0);
            return _map;
        }
        private java.lang.String _map;
    }

</p>
</details>