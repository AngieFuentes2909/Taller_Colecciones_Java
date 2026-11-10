import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class SistemaBiblioteca {
    private Map<String, Material> catalogo = new HashMap<>();
    private Map<String, List<String>> listaEspera = new HashMap<>();
    private Set<String> miembros = new HashSet<>();

    public void registrarMaterial(Material m) {
        catalogo.put(m.getCodigo(), m);
    }

    public Material buscarMaterial(String codigo) {
        return catalogo.get(codigo);
    }

    public void mostrarCatalogo() {
        for (Map.Entry<String, Material> entry : catalogo.entrySet()) {
            entry.getValue().mostrarInfo();
        }
    }

    public void agregarEspera(String codigoMaterial, String usuario) {
        listaEspera.putIfAbsent(codigoMaterial, new ArrayList<>());
        listaEspera.get(codigoMaterial).add(usuario);
    }

    public String siguienteEnEspera(String codigoMaterial) {
        List<String> cola = listaEspera.get(codigoMaterial);
        if (cola != null && !cola.isEmpty()) {
            return cola.remove(0);
        }
        return null;
    }

    public boolean registrarMiembro(String usuario) {
        return miembros.add(usuario);
    }

    public boolean tieneMembresia(String usuario) {
        return miembros.contains(usuario);
    }

    public static void main(String[] args) {
        SistemaBiblioteca biblioteca = new SistemaBiblioteca();

        // hashMap
        Libro libro1 = new Libro("AK-001", "Princesa y el sapo", 2008, true, "Robert", "978-0132350884");
        Revista revista1 = new Revista("RV-001", "today", 2026, true, 20, "Tecnología");

        biblioteca.registrarMaterial(libro1);
        biblioteca.registrarMaterial(revista1);

        System.out.println("catalogo inicial");
        biblioteca.mostrarCatalogo();

        // existente e inexistente
        Material encontrado = biblioteca.buscarMaterial("BK-001");
        if (encontrado != null) {
            System.out.println("\nencontrado: " + encontrado.getTitulo());
        }

        Material noExiste = biblioteca.buscarMaterial("BK-999");
        System.out.println("¿Código inexistente?: " + (noExiste == null ? "No, retorna null de forma segura" : ""));

        // HashSet
        System.out.println("\nregistro de los miembros");
        System.out.println("registrando a Ana: " + biblioteca.registrarMiembro("Ana")); // true
        System.out.println("registrando a Carlos: " + biblioteca.registrarMiembro("Carlos")); // true
        System.out.println("intentando registrar a Ana otra vez: " + biblioteca.registrarMiembro("Ana")); // false (duplicado)[cite: 7, 8]
        System.out.println("¿Ana tiene membresía?: " + biblioteca.tieneMembresia("Ana")); // true

        //arrayList lista de espera
        System.out.println("\nlista de espera");
        libro1.setDisponible(false); // lo marque como prestao

        biblioteca.agregarEspera("BK-001", "Lucia");
        biblioteca.agregarEspera("BK-001", "Mateo");
        biblioteca.agregarEspera("BK-001", "Sofia");

        System.out.println("atendiendo al primer en espera: " + biblioteca.siguienteEnEspera("BK-001")); // Debe ser Lucía
        System.out.println("atendiendo al siguiente en espera: " + biblioteca.siguienteEnEspera("BK-001")); // Debe ser Mateo
    }


}
