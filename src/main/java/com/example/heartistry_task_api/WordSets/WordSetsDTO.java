package com.example.heartistry_task_api.WordSets;

public class WordSetsDTO {
    private Integer key1;
    private String key2;
    private String key3;
    private String key4;
    private String key5;
    private String key6;

    public WordSetsDTO(WordSets wordSets) {
        this.key1 = wordSets.getKey1();
        this.key2 = wordSets.getKey2();
        this.key3 = wordSets.getKey3();
        this.key4 = wordSets.getKey4();
        this.key5 = wordSets.getKey5();
        this.key6 = wordSets.getKey6();
    }

    public Integer getKey1() {
        return key1;
    }
    public String getKey2() {
        return key2;
    }
    public String getKey3() {
        return key3;
    }
    public String getKey4() {
        return key4;
    }
    public String getKey5() {
        return key5;
    }
    public String getKey6() {
        return key6;
    }
    public void setKey1(Integer key1) {
        this.key1 = key1;
    }
    public void setKey2(String key2) {
        this.key2 = key2;
    }
    public void setKey3(String key3) {
        this.key3 = key3;
    }
    public void setKey4(String key4) {
        this.key4 = key4;
    }
    public void setKey5(String key5) {
        this.key5 = key5;
    }
    public void setKey6(String key6) {
        this.key6 = key6;
    }
}
