package org.sang;

/**
 * 2不同系统下的Bean
 */
public class WindowsListService implements ListService{
    public String showListCmd() {
        return "dir";
    }
}
