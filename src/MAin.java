import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MAin {

    public static void main(String[] args) {
       Persona p1 = new Persona(1,"Jorge","Mestre", LocalDate.of(2015,4,1));
       Persona p2 = new Persona(2,"Beatriz","Perez", LocalDate.of(1992,6,4));
       Persona p3 = new Persona(3,"Margot","Estefano", LocalDate.of(1932,7,20));
       Persona p4 = new Persona(4,"Margarita","Mestre", LocalDate.of(1960,6,15));

       //Producto
        Producto pro1 = new Producto(1,"arroz", 10.50);
        Producto pro2 = new Producto(2,"frijol", 20.50);
        Producto pro3 = new Producto(3,"azùcar", 7.00);
        Producto pro4 = new Producto(4,"cafè", 10.00);

        List<Persona> personas = new ArrayList<>();
        personas.add(p1);
        personas.add(p2);
        personas.add(p3);
        personas.add(p4);
        List<Producto> productos = Arrays.asList(pro1,pro2,pro3,pro4);

        System.out.println("PERSONAS");
        //personas.forEach(System.out::println);
        //personas.forEach(p -> System.out.println(p));
        personas.forEach(p->{
            System.out.println(p.getId());
            System.out.println(p.getNombre());
            System.out.println(p.getApellido());
            System.out.println(p.getFecha());
        });

        System.out.println("PRODUCTOS");
        //productos.forEach(System.out::println);
        //productos.forEach(p -> System.out.println(p));
        productos.forEach(p ->{
            System.out.println(p.getId());
            System.out.println(p.getNombre());
            System.out.println(p.getPrecio());
        });
//|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        /******FILTER (param: predicate
        (el predicate es una expresion booleana, una expresión que retorna true or false eje: p.getFecha())>18))
         filter retorna un nuevo stream
        Se utiliza para hacer consultas de acuerdo a una condición dada, parecido a los select en sql
        final de un filter se debe hacer un collec para almacenar en una nueva lista la info obtenida
         */
        //devuelve una lista de personas mayores de 18
        List<Persona> mayores18 = personas.stream()
                .filter(p -> MAin.getAge(p.getFecha())>18)
                .collect(Collectors.toList());
        //MAin.printListPersona(mayores18);
//|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        /*******MAP (param: function
        esta función retorna cualquier tipo de dato eje: p.getFecha())
         map retorna un nuevo stream
         Se utiliza para transformar los elementos de la colección, ejemplo, si se necesita transformar de un
        valor a otro valor, para concatenar variables, para hacer una lógica de multiplicación, suma,....
        es decir que a partir de un tipo de dato A obtengo un tipo de dato B
        por ejemplo, acá a aprtir de una lista de tipo persona obtengo una lista de entros que hacen referencia a
        las edades de esas personas
        */
        //devuel todas las edades de la lista de personas
        List<Integer> edades = personas.stream()
                .map(p -> MAin.getAge(p.getFecha()))
                .collect(Collectors.toList());
        //MAin.printList(edades);

        //devuelve las edades de las personas mayores de 18
        List<Integer> edades18 = personas.stream()
                .filter(p -> MAin.getAge(p.getFecha())>18)
                .map(p -> MAin.getAge(p.getFecha()))
                .collect(Collectors.toList());
       // MAin.printList(edades18);

        //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        /*******SORTED (param: comparator*/

//deuelve la lista de personas ordenda por nombre
       List<Persona> ordenadaMayorMenor = personas.stream()
                .sorted((x,y) -> (x.getNombre().compareTo(y.getNombre())))
                .collect(Collectors.toList());
      // MAin.printListPersona(ordenadaMayorMenor);

        //devuelve la lista de personas ordendas por edad
        List<Persona> ordenadaMayorMenorEdad = personas.stream()
                .sorted((x,y) -> (x.getFecha().compareTo(y.getFecha())))
                .collect(Collectors.toList());
        //MAin.printListPersona(ordenadaMayorMenor);

        List<Persona> ordenadaMayorMenorID = personas.stream()
                .sorted((x,y) -> (String.valueOf(y.getId()).compareTo(String.valueOf(x.getId()))))
                .collect(Collectors.toList());
        //MAin.printListPersona(ordenadaMayorMenorID);

        //devuelve la lista de id ordenada de mayor a menor
        List<Integer> ordenadaMayorMenorID1 = personas.stream()
                .map(p -> p.getId())
                .sorted((x,y) -> (String.valueOf(y).compareTo(String.valueOf(x))))
                .collect(Collectors.toList());
        //MAin.printList(ordenadaMayorMenorID1);

       //|||||||||||||||||||||||||||||||||||||||||||||||||||||||
       // ANYMATCH
       /* NO evalua todo el stream, termina en la coincidencia
       retorna un boolean
       */
        Predicate<Persona> startWithPerson = p -> p.getNombre().startsWith("J");
        boolean coincde = personas.stream()
                .anyMatch(startWithPerson);
        //System.out.println(coincde);

        //|||||||||||||||||||||||||||||||||||||||||||||||||||
        // ALLMATCH
       /*  Evalua todo el stream,
       busca que la lista completa comienze con un caracter determinado,
       en este caso "J"
       si la lista completa comienza con el caracter retorna true, si no retorna false
       retorna un boolean
       */
        boolean coincde1 = personas.stream()
                .allMatch(startWithPerson);
        //System.out.println(coincde1);
        //|||||||||||||||||||||||||||||||||||||||||||||||||||
        // NONEMATCH
       /*  Evalua todo el stream, buscando que en la lista completa
        ningún  nombre comienze con un caracter determinado,
       en este caso "J"
       retorna un boolean
       */
        boolean coincde2 = personas.stream()
                .noneMatch(p -> p.getNombre().startsWith("J"));
        //System.out.println(coincde2);

        //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        //LIMIT/SKIP
        /*
        * Operadores para realizar el paginado de listas
        * */
        int pageNumber = 0;
        int sizeList = 2;
          List<Persona> perosnaPagina = personas.stream()
                  .skip(pageNumber * sizeList)
                  .limit(sizeList)
                  .collect(Collectors.toList());
        //MAin.printListPersona(perosnaPagina);
        //||||||||||||||||||||||||||||||||||||||||||||||||
        // COLLECTOR para GROUPBY

        Map<Double,List<Producto>> productosXprecio = productos.stream()
                .filter(p -> p.getPrecio()>10)
                .collect(Collectors.groupingBy(Producto::getPrecio));
        //productosXprecio.values().forEach(p -> MAin.printListProducto(p));

        //COLLECTOR para COUNT
        Map<String,Long> count_productosXnombre = productos.stream()
                               .collect(Collectors.groupingBy(Producto::getNombre, Collectors.counting()));
        //System.out.println(count_productosXnombre);

        //Productos agrupados por nombre y sumar precios
        Map<String,Double> productosXnombre_sumarPrecio = productos.stream()
                .collect(Collectors.groupingBy(Producto::getNombre, Collectors.summingDouble(Producto::getPrecio)));
        //System.out.println(productosXnombre_sumarPrecio);

        //Para estadisticas
        DoubleSummaryStatistics mystatistics = productos.stream()
                .collect(Collectors.summarizingDouble(Producto::getPrecio));
        //System.out.println(mystatistics);

        //Para obtener la suma total del precio de todos los productos
        Optional<Double> sumatotal=productos.stream()
                .map(Producto::getPrecio)
                .reduce(Double::sum);
        System.out.println(sumatotal.get());

    }


    //metodo para calcular el número de años de una persona
    public static int getAge(LocalDate fecha){
        return Period.between(fecha,LocalDate.now()).getYears();
    }

    //metodo para imprimir
    public static void printList(List<?> list){
        list.forEach(System.out::println);
    }
    public static void printListPersona(List<Persona> list){
        list.forEach(l -> {
            System.out.println(l.getId());
            System.out.println(l.getNombre());
            System.out.println(l.getApellido());
            System.out.println(l.getFecha());
        });
    }

    public static void printListProducto(List<Producto> list){
        list.forEach(l -> {
            System.out.println(l.getId());
            System.out.println(l.getNombre());
            System.out.println(l.getPrecio());

        });
    }

    public static boolean mayor(int x, int y){
        if(x > y)
            return true;
        return false;
    }
}
