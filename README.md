# Instanciando entidades en Java

<p>
    Podemos crear nuestras entidades automáticamente en la base de datos con anotaciones. Cada anotación representa un valor en la tabla.
</p>

### Anotando las entidades

Creando las anotaciones


```java
// @OneToOne
public @interface OneToOne {
    Class<?> entity();
}

// @Entity
public @interface Entity {}

// @Integer
public @interface Integer {
    boolean unique() default false; 
}

// @Varchar
public @interface Varchar {
    int length();

    boolean unique() default false; 
}

// @PrimaryKey
public @interface PrimaryKey {}

// @Timestamp
public @interface Timestamp {
    boolean creationTimestamp() default true;
}
```

### Anotando las entidades

```java
@Entity
public class Users  {
    @PrimaryKey
    private int id;

    @Varchar(length = 32, unique = true)
    private String username;

    @Varchar(length = 32)
    private String password;

    @Timestamp()
    private Date date;

    @OneToOne(entity = Persons.class)
    private Persons persons;

    // Getters & Setters
}
```

```java
@Entity
public class Persons {
    @PrimaryKey
    private int id;
    
    @Varchar(length = 32)
    private String name;

    @Varchar(length = 50)
    private String lastname;

    @Integer
    private int dni;

    @Integer(unique = true)
    private int age;

    @Timestamp
    private Date date;

    // Getters & Setters
}
```


### Insertando la relación @OneToOne.

Al insertar una entidad, si la relación <b>@OneToOne</b> está presente en el campo, se tomará su valor y se insertará el valor primeramente en la base de datos.

<b>Persons >> Users</b>

```java
public static <T> String getFieldToInsert(T clazz) throws Exception {

    //...

    if (field.isAnnotationPresent(PrimaryKey.class)) {
        continue;
    }

        Object value = field.get(clazz);
        
        String fieldName = field.getName(); 
        
         if (field.isAnnotationPresent(OneToOne.class)) {
            String relationName = field.getType().getSimpleName();
            fieldName = relationName + "_id";

            String sqlQuery = getFieldToInsert(value);
            ResultSet result = ExecuteQuery.query("INSERT INTO %s %s RETURNING id", 
            value.getClass().getSimpleName(), sqlQuery);

            if (result.next()) {
                value = result.getInt("id");
            }
        }

        rows.add(fieldName);

        values.add(value);
    }

    return String.format("(%s) VALUES (%s)",
        String.join(",", rows),
        values.stream().map((object) -> {
            if (object instanceof String) {
                return String.format("'%s'", object);
            }

            return object.toString();
        }).collect(Collectors.joining(","))
    );
}
```

### Revisando su valor
Al ejecutar este bloque de código en PostgreSQL, podemos obtener sus valores insertados.

```postgres
DO $$
	DECLARE
		result RECORD;
		sql CURSOR FOR SELECT p.name, p.lastname, u.username, u.password
		FROM users AS u 
		LEFT JOIN persons AS p
		ON p.id = u.persons_id;
		
	BEGIN
		OPEN sql;
			FETCH sql INTO result;
			RAISE NOTICE '%', jsonb_agg(result);
		CLOSE sql;
	END 
$$
```

```postgres
NOTICE:  [{"name": "Cristian", "lastname": "Moreno", "password": "123456789", "username": "username"}]
DO

Query returned successfully in 89 msec.
```