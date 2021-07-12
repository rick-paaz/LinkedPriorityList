package model;

/**
 * This interface describes an abstract data type to store elements where
 * indexes represent priorities and the priorities can change in several ways.
 * 
 * @author Rick Mercer
 * @author YOUR NAME
 * 
 * @param <T>
 *          The type of all elements stored in this collection
 */
public interface PriorityList<T> {

  /**
   * Return the number of elements currently in this PriorityList
   * 
   * @return The number of elements in this PriorityList
   */
  public int size();

  /**
   * Return true if there are zero elements in this PriorityList
   * 
   * @return true if size() == 0 or false if size() > 0
   */
  public boolean isEmpty();

  /**
   * If possible, insert the element at the given index. If index is out of the
   * valid range of 0..size(), throw new IllegalArgumentException(); When size is
   * 3, the only possible values for index are 0, 1, 2, AND 3 because you can add
   * an element as the new last.
   * 
   * @param index
   *          The index of the element to move.
   * @param el
   *          The element to insert
   * @throws IllegalArgumentException
   */
  public void insertElementAt(int index, T el) throws IllegalArgumentException;

  /**
   * If possible, return a reference to the element at the given index. If index
   * is out of the valid range of 0..size()-1, throw new
   * IllegalArgumentException(); When size is 3, the only possible values for
   * index are 0, 1, and 2.
   * 
   * @param index
   *          The index where the element gets inserted into. All other elements
   *          must remain this list.
   * @return A reference to the element at index index.
   * @throws IllegalArgumentException
   */
  public T getElementAt(int index) throws IllegalArgumentException;

  /**
   * If possible, remove the element at the given index. If index is out of the
   * valid range of 0..size()-1, throw new IllegalArgumentException(); When size
   * is 3, the only possible values for index are 0, 1, and 2.
   * 
   * @param index
   *          The index of the element to remove. All other elements must remain.
   * @throws IllegalArgumentException
   */
  public void removeElementAt(int index) throws IllegalArgumentException;

  /**
   * If possible, swap the element located at index with the element at index + 1.
   * An attempt to lower the priority of the element at index size()-1 has no
   * effect. If index is out of the valid range of 0..size()-1, throw new
   * IllegalArgumentException(); When size is 3, the only possible values for
   * index are 0, 1, and 2.
   * 
   * @param index
   *          The index of the element to be changed with one next to it.
   * @throws IllegalArgumentException
   */
  public void lowerPriorityOf(int index) throws IllegalArgumentException;

  /**
   * If possible, swap the element located at index with the element at index-1.
   * An attempt to raise the priority at index 0 has no effect. If index is out of
   * the valid range of 0..size()-1, throw new IllegalArgumentException(); When
   * size is 3, the only possible values for index are 0, 1, and 2.
   * 
   * @param index
   *          The index of the element to be changed with one next to it.
   * @throws IllegalArgumentException
   */
  public void raisePriorityOf(int index) throws IllegalArgumentException;

  /**
   * Return a copy of all elements as an array of Objects that is the size of this
   * PriorityList and in the same order. If there are no elements in this list,
   * return new Object[0]; A change to the return value must not affect this
   * PriorityList object.
   * 
   * @return An array of Objects where capacity == size()
   */
  public Object[] toArray();

  /**
   * If possible, move the element at the given index to the end of this list. An
   * attempt to move the last element to the last has no effect. If the index is
   * out of the valid range 0..size()-1 throw new IllegalArgumentException(); When
   * size is 3, the only possible values for index are 0, 1, and 2.
   * 
   * @param index
   *          The index of the element to be changed to be the last index.
   * @throws IllegalArgumentException
   */
  public void moveToLast(int index) throws IllegalArgumentException;

  /**
   * If possible, move the element at the given index to the front of this list An
   * attempt to move the top element to the top has no effect. If the index is out
   * of the valid range of 0..size()-1, throw new IllegalArgumentException(); When
   * size is 3, the only possible values for index are 0, 1, and 2.
   * 
   * @param index
   *          The index of the element to be changed to the first index.
   * @throws IllegalArgumentException
   */
  public void moveToTop(int index) throws IllegalArgumentException;
}