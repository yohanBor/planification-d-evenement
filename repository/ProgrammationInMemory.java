package repository;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


import application.Programmation;
import domain.ID;
import domain.ProgrammationRepository;

//repository of aggregates
public class ProgrammationInMemory implements ProgrammationRepository {

    Set <Programmation> memory; //used of set to avoid having two times the same aggregate

    public ProgrammationInMemory() {
        this.memory = new HashSet<Programmation>();
    }

    public void save(Programmation programmation) {
        memory.add(programmation);
        System.out.println("Save in memory done." + "\nNumbers of aggregates saved for now : " + memory.size());
    }

   
    public void update(Programmation programmation) {
        for (Programmation prog : memory) {
            if (prog == programmation) {
                memory.remove(prog);
                memory.add(programmation);
//                System.out.println("Update is okay.");
            }
        }
    }

    public Programmation findProgById(ID ProgId) {
        for (Programmation prog : memory) {
            if(prog.getId() == ProgId){
                System.out.println("La sauvegarde a bien été trouvée ");
                return prog;
            }
        }
        return null;
    }

    public void showMemory(){
        System.out.println("Exploration de la mémoire : ");
        Iterator<Programmation> it = memory.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
    
}
