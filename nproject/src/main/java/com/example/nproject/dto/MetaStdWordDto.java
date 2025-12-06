package com.example.nproject.dto;

public class MetaStdWordDto {
    private Integer wordId;
    private String stdWordName;
    private String engAbbrName;
    private String engFullName;
    private String definition;
    private String domainType;
    private String useYn;
    
    // Pagination and Search fields
    private int page = 1;
    private int size = 10;
    private int offset;

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
}
