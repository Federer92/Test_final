package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.PagingList;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class todo extends Model
{
        @Id
        public long id ;
        public String commentaire ;
        public String username ;
        public Date creationDate ;
        
        public static Finder<Long, todo> find = new Finder<Long, todo>(Long.class, todo.class);
        
        public static List<todo> all()
        {
                //return find.all();
                return find.setMaxRows(2).findList();
        }
        
        public static List<todo> findByUsername(String username)
        {
                return find.where().eq("username", username).findList();
        }
        
        public static todo create(todo todo)
        {
                todo.creationDate = new Date();
            todo.save();
            return todo;
        }
        
        public static List<todo> findNext(int from, int nb)
        {
                return find.setFirstRow(from).setMaxRows(nb).findList();
        }
}
