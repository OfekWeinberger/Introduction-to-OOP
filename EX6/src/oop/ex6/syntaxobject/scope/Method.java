package oop.ex6.syntaxobject.scope;

public class Method extends Scope {
    private String name;

    public String getName() {
        return name;
    }

    public Method(String name){
        super(Root.Instant(),null,null);
        this.name = name;
    }
}
