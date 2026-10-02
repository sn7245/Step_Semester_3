package Week5.ClassProblems;

public class SubclassTicketAccess class SubclassTicketAccess {

    static String classifyAccess(String fieldModifier, String accessorContext) {

        if (fieldModifier.equals("private"))
            return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

        if (fieldModifier.equals("default"))
            return (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE"))
                    ? "ALLOWED" : "DENIED";

        if (fieldModifier.equals("protected"))
            return (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE") ||
                    accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"))
                    ? "ALLOWED" : "DENIED";

        if (fieldModifier.equals("public"))
            return "ALLOWED";

        return "DENIED";
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));

        System.out.println(classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
    }
}{
}
