package com.mxue.generator.single.utils;

/**
 * code name format
 */
public class CodeNameFormatUtils {

    /**
     * name format camel
     * @param name name
     * @return camel name
     */
    public static String formatCamel(String name) {
        String[] parts = name.split("_");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            if (i == 0) {
                result.append(parts[i].toLowerCase());
            } else {
                result.append(parts[i].substring(0, 1).toUpperCase()).append(parts[i].substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /**
     * pascal name format
     *
     * @param name name
     * @return pascal name
     */
    public static String formatPascal(String name) {
        String[] parts = name.split("_");
        StringBuilder result = new StringBuilder();
        for (String part : parts) {
            result.append(part.substring(0, 1).toUpperCase()).append(part.substring(1).toLowerCase());
        }
        return result.toString();
    }

    /**
     * pascal name convert camel
     * @param name pascal name
     * @return camel name
     */
    public static String pascalConvertCamel(String name) {
       if (name == null) {
           return null;
       }

       if (name.isEmpty()) {
           return name;
       }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            if (i == 0) {
                result.append(Character.toLowerCase(name.charAt(i)));
            } else {
                result.append(name.charAt(i));
            }
        }
        return result.toString();
    }

}
