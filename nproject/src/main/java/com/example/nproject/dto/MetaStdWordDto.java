package com.example.nproject.dto;

public class MetaStdWordDto {
    private Integer wordId;
    private String stdWordName;
    private String engAbbrName;
    private String engFullName;
    private String definition;
    private String domainType;
    private String useYn;
    private String coherenceYn;
    private String synonyms;
    private String forbiddenWords;
    private int page = 1;
    private int size = 10;
    private int offset;

    // UI Persistence
    private String text1;
    private String text2;
    private String text3;
    private String text4;

    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }

    public String getStdWordName() {
        return stdWordName;
    }

    public void setStdWordName(String stdWordName) {
        this.stdWordName = stdWordName;
    }

    public String getEngAbbrName() {
        return engAbbrName;
    }

    public void setEngAbbrName(String engAbbrName) {
        this.engAbbrName = engAbbrName;
    }

    public String getEngFullName() {
        return engFullName;
    }

    public void setEngFullName(String engFullName) {
        this.engFullName = engFullName;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getDomainType() {
        return domainType;
    }

    public void setDomainType(String domainType) {
        this.domainType = domainType;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }

    public String getForbiddenWords() {
        return forbiddenWords;
    }

    public void setForbiddenWords(String forbiddenWords) {
        this.forbiddenWords = forbiddenWords;
    }

    public String getCoherenceYn() {
        return coherenceYn;
    }

    public void setCoherenceYn(String coherenceYn) {
        this.coherenceYn = coherenceYn;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getOffset() {
        return (page - 1) * size;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    public String getText4() {
        return text4;
    }

    public void setText4(String text4) {
        this.text4 = text4;
    }
}
