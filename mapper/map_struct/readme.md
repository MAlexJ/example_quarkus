### MapStruct

#### How to Use MapStruct in Quarkus

1. Add dependencies

```
dependencies {
   implementation("org.mapstruct:mapstruct:1.5.5.Final")
   annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
}
```

2. Create Mapper Interface

```
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "jakarta") // Use "jakarta" for CDI
public interface FruitMapper {
    FruitDto toDto(FruitEntity entity);
    FruitEntity toEntity(FruitDto dto);
}
```

or

```
@Mapper(componentModel = "jakarta")
public abstract class FruitMapper {

  public abstract FruitResponse toResponse(FruitEntity entity);

  @Mapping(target = "id", expression = "java(generateId())")
  public abstract FruitEntity toEntity(FruitRequest dto);

  protected String generateId() {
    return UUID.randomUUID().toString();
  }
}
```

You can also use:

- componentModel = "cdi" → for Quarkus prior to 3.x
- componentModel = "jakarta" → for Quarkus 3.x+ (uses Jakarta CDI)

3. Inject Mapper in Quarkus Beans

```
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FruitService {

    @Inject
    FruitMapper mapper;

    public FruitDto getDto(FruitEntity entity) {
        return mapper.toDto(entity);
    }
}
```

#### Notes

MapStruct works perfectly with Quarkus's JVM and native modes.
If you're using Quarkus Dev Mode (./mvnw quarkus:dev),
you might need to trigger a rebuild when changing mapper definitions.

#### Optional: Lombok Integration

If you use Lombok in DTOs/entities, ensure you include:

```
annotationProcessor("org.projectlombok:lombok")
annotationProcessor("org.mapstruct:mapstruct-processor")
```

So Lombok and MapStruct don't conflict during compilation.

Let me know if you'd like a full working example (entity, dto, mapper, service, and resource)
for Quarkus + MapStruct.