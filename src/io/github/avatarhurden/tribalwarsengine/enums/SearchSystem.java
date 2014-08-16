package io.github.avatarhurden.tribalwarsengine.enums;

/**
 * @author Wesley Nascimento
 */
public enum SearchSystem {
    SIMPLE(1, "Pesquisa simplificada"),
    THREE_LEVELS(3, "Sistema de 3 n�veis"),
    TEN_LEVELS(10, "Sistema cl�ssico");

    private int research;
    private String name;

    private SearchSystem(int availableResearch, String name) {
        this.research = availableResearch;
        this.name = name;
    }

    public int getResearch() {
        return research;
    }

    public String getName() {
        return name;
    }

    /**
     * Converte um numero inteiro em um Objeto do enum SearchSystem
     *
     * @param searchs - Numero de pesquisas
     * @return
     */
    public static SearchSystem ConvertInteger(int searchs) {
        switch (searchs) {
            case 1:
                return SearchSystem.SIMPLE;
            case 3:
                return SearchSystem.THREE_LEVELS;
            case 10:
                return SearchSystem.TEN_LEVELS;
        }

        //por padr�o retorna
        return SearchSystem.SIMPLE;
    }
}
