import java.io.Serializable;
import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String filePath = "src/tree.txt";
//        FamilyTree tree = testTree();
        FamilyTree tree = load(filePath);

        System.out.println(tree);

        save(tree, filePath);

    }

    private static FamilyTree load(String filePath) {
        Writable writable = new FileHandler();
        return (FamilyTree) writable.read(filePath);
    }

    private static void save(FamilyTree tree, String filePath) {
        Writable writable = new FileHandler();
        writable.save(tree, filePath);
    }


    private static FamilyTree testTree(){
        FamilyTree tree = new FamilyTree();

        Human oxana = new Human("Оксана", Gender.Female, LocalDate.of(1968, 6, 16));
        Human kostya = new Human("Константин", Gender.Male, LocalDate.of(1965, 10,17));

        tree.add(oxana);
        tree.add(kostya);

        Human vera = new Human("Вера", Gender.Female, LocalDate.of(1987,9,25),
                kostya, oxana);

        tree.add(vera);

        Human grandMother = new Human("Нина", Gender.Female, LocalDate.of(1946, 4,11));
        grandMother.addChild(oxana);

        tree.add(grandMother);

        return tree;
    }
}