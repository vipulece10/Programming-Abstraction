class Merger:
    def merge(tree, tree1):
        iter1 = iter(tree)

        iter2 = iter(tree1)
        list=[]

        try:
            p = next(iter1)
        except Exception as e:
            p = None

        try:
            q = next(iter2)
        except Exception as e:
            q = None

        if p == "":
            p = None
        if q == "":
            q = None

        while 1:
            if p is None or q is None:
                break
            if p <= q:
                list.append(p)
                try:
                    p = next(iter1)
                except Exception as e:
                    p = None
            else:
                list.append(q)
                try:
                    q = next(iter2)
                except Exception as e:
                    q = None

        if p is None and q is not None:
            list.append(q)
            while 1:
                try:
                    q = next(iter2)
                except Exception as e:
                    q = None
                if q is not None:
                    list.append(q)
                else:
                    break

        if q is None and p is not None:
            list.append(p)
            while 1:
                try:
                    p = next(iter1)
                except Exception as e:
                    p = None

                if p is not None:
                    list.append(p)
                else:
                    break
        return list

class Node:
    def __init__(self, data=None):
        self.left = None
        self.right = None
        self.data = data
        self.array = []
        self.count = 0

    def __iter__(self):
        self.inorder(self)
        for data in self.array:
            if self.count < len(self.array):
                yield data
                self.count += 1
            else:
                raise StopIteration

    def inorder(self, root):
        if root is None:
            return
        self.inorder(root.left)
        self.array.append(root.data)
        self.inorder(root.right)
        return self.array


class BinarySearchTree:
    def __init__(self, name, root):

        self.root = None
        self.name = name


    def insert(self, val=None):
        self.root = self.insert_recursive(self.root, val)

    def insert_recursive(self, root, val):

        if root is None:
            root = Node(val)
            return root
        if val < root.data:
            root.left = self.insert_recursive(root.left, val)
        elif val > root.data:
            root.right = self.insert_recursive(root.right, val)

        return root


    def add_all(self, *data):
        if len(data) == 0:
            self.insert("")
        else:
            for element in data:
                self.insert(element)

    def __iter__(self):
        return self.root.__iter__()


    def __str__(self):
        return "["+self.name+"] " + self.print(self.root)

    def print(self, root):
        if root is None:
            return ""
        elif root.left is not None and root.right is not None:
           return str(root.data) + " " + "L:(" + str(self.print(root.left)) + ")" + " " + "R:(" + str(self.print(root.right)) + ")"
        elif root.left is not None and root.right is None:
           return str(root.data) + " " + "L:(" + str(self.print(root.left)) + ")"
        elif root.left is None and root.right is not None:
            return str(root.data) + " " + "R:(" + str(self.print(root.right)) + ")"
        else:
           return str(root.data) + ""



if __name__ == "__main__":
    tree = BinarySearchTree(name="Oak", root=Node())
    #tree.add_all(5, 3, 9, 0)  # adds the elements in the order 5, 3, 9, and then 0

    #print(tree)

    tree1 = BinarySearchTree(name="Maple", root=Node())
    tree1.add_all(3, 2, 1)  # adds the elements in the order 5, 3, 9, and then 0

   # for x in tree.root:
    #    print(x)

    #for x in tree1.root:
     #   print(x)


    print(Merger.merge(tree, tree1))

