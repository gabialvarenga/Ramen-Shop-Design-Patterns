package br.lpm.types;

import br.lpm.core.Ramen;

public class RamenGrande extends Ramen {
    public RamenGrande(String proteina){
        super(proteina);
        this.setPrecoBase(15.90);
    }
}