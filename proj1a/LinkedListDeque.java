public class LinkedListDeque<T> {
    private class StuffNode{
        T item;
        StuffNode next;
        StuffNode prev;

        public StuffNode(T item, StuffNode next, StuffNode prev){
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    //First item if it exists , its at sentinel.next.
    //last item if it exists , its at sentinel.prev.

    StuffNode sentinel;
    int size;

    //Creates an empty LinkedList.
    public LinkedListDeque(){
        sentinel = new StuffNode((T)"null",sentinel,sentinel);
        size = 0;
    }

    public LinkedListDeque(T item){
        sentinel = new StuffNode((T)"null",sentinel,sentinel);
        sentinel.next = new StuffNode(item,sentinel,sentinel);
        sentinel.prev = sentinel.next; //sentinel.prev should point at last node.
        size = 1;
    }

    public void addFirst(T item){


        StuffNode p = new StuffNode(item,sentinel.next,sentinel);
        if(!isEmpty()){
            StuffNode oldFirst = sentinel.next;
            oldFirst.prev = sentinel.next;
        }
        sentinel.next = p;
        size += 1;
    }

    public void addLast(T item){
        this.size += 1;
        sentinel.prev.next = new StuffNode(item,sentinel,sentinel.prev);
        sentinel.prev = sentinel.prev.next;
    }

    public boolean isEmpty(){
        if(this.size == 0){
            return true;
        }

        return false;
    }

    public int size(){
        return this.size;
    }

    public void printDeque(){
        for (StuffNode i = sentinel.next; i != sentinel; i = i.next){
            System.out.print(i.toString() + " ");
        }
    }


    //Removes and returns the item at the front of the deque.
    // If no such item exists, returns null.
    public T removeFirst(){
        if (isEmpty()){
            return null;
        }

        T itemToRemove = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size = size - 1;
        return itemToRemove;
    }


    //Removes and returns the item at the back of the deque.
    //If no such item exists, returns null.
    public T removeLast(){
      if(isEmpty()){
          return null;
      }

      T itemAtLast = sentinel.prev.item;
      sentinel.prev = sentinel.prev.prev;
      sentinel.prev.next = sentinel;
      size = size - 1;
      return itemAtLast;
    }

    //Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    //If no such item exists, returns null. Must not alter the deque
    public T get(int index){
        if(index < size || index < 0){
            return null;
        }

        StuffNode p = sentinel.next;
        for (int i = 0; i != index; i++){
            p = p.next;
        }

        return p.item;
    }

    public T getRecursive(int index){
        if(index < size || index < 0){
            return null;
        }
        return getRecursive(sentinel.next,index);
    }

    private T getRecursive(StuffNode node,int index){
        if (index == 0){
            return sentinel.next.item;
        }
        return getRecursive(node.next,index--);
    }

}
