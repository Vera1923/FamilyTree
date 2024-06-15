import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FamilyTree implements Serializable, Iterable<Human>{
    private  long humansId;
    private List<Human> humanList;

    public FamilyTree(){
        this(new ArrayList<>());
    }

    public FamilyTree(List<Human> humanList) {
        this.humanList = humanList;
    }

    public boolean add(Human human){
        if (human == null) {
            return false;
        }
        if (!humanList.contains(human)){
            humanList.add(human);
            human.setId(humansId++);

            addToParents(human);
            addToChildren(human);

            return true;
        }
        return  false;
    }

    private void addToParents(Human human){
        for (Human parent: human.getParents()){
            parent.addChild(human);
        }
    }

    private void addToChildren(Human human){
        for (Human child: human.getChildren()){
            child.addParent(human);
        }
    }

    public List<Human> getByName(String name){
        List<Human> res = new ArrayList<>();
        for (Human human: humanList){
            if (human.getName().equals(name)){
                res.add(human);
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return getInfo();
    }

    public String getInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append("В дереве: ");
        sb.append(humanList.size());
        sb.append(" объектов: \n");
        for (Human human: humanList){
            sb.append(human);
            sb.append("\n");
        }
        return sb.toString();
    }

    public void sortByName(){
        humanList.sort(new HumanComparatorByName());
    }

    public void sortByBirthDate(){
        humanList.sort(new HumanComparatorByBirthDate());
    }

    @Override
    public Iterator<Human> iterator() {
        return new FamilyTreeIterator(humanList);
    }
}
