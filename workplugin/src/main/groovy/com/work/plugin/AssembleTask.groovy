package com.work.plugin

public class AssembleTask {
    boolean isAssemble = false
    boolean isDebug = false
    List<String> modules = new ArrayList<>()

    public void println(){
        System.println("isAssemble : "+isAssemble+"  isDebug : "+isDebug)
    }
}