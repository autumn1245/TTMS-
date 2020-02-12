/**
 * 
 */
package idao;

import java.util.List;

import entity.Studio;


/**
 * @author Administrator
 *
 */
public interface IStudioDAO {
	public int insert(Studio stu);
	public int update(Studio stu);
	public int delete(int ID);
	public List<Studio> select(String condt); 
}
