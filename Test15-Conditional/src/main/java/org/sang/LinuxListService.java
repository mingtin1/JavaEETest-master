package org.sang;

/**
 * 2不同系统下的Bean
 *
 */
public class LinuxListService implements ListService {
    public String showListCmd() {
        return "ls";
    }
}
