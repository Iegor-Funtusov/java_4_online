package ua.com.alevel;

import org.reflections.Reflections;
import org.reflections.Store;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DIFrameworkSearcher {

    private final Reflections scanner;
    private Set<String> interfaces;

    public DIFrameworkSearcher(String rootPackage) {
        this.scanner = new Reflections(rootPackage);
        init();
    }

    private void init() {
        Store store = this.scanner.getStore();
        Collection<Map<String, Set<String>>> collection = store.values();
        Set<String> allInterfacesName = new HashSet<>();
        collection.forEach((v) -> {
            Set<String> strings = v.keySet();
            allInterfacesName.addAll(strings);
        });
        this.interfaces = allInterfacesName.stream().filter(this::isServiceClass).collect(Collectors.toSet());
    }

    private boolean isServiceClass(String className) {
        return className.endsWith("Controller") || className.endsWith("Service") || className.endsWith("Dao");
    }

    public Set<String> getInterfaces() {
        return interfaces;
    }

    public Reflections getScanner() {
        return scanner;
    }
}
