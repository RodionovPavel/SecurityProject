INSERT INTO question16 VALUES ('97b77788-2e17-4035-bb21-53e78f98051a', 1, 'Конструкторы', 'Какое значение по умолчанию имеет переменная типа short?

A) - 0.0
B) - 0
C) - Null
D) - Никакое', '', '', 'A', 'И это правильный ответ!', 'Увы, но это не так. Вот ссылка для информации https://javarush.com/groups/posts/1391-konstruktorih-klassov-java-jdk-15');
INSERT INTO question16 VALUES ('be6ecd5b-e967-4d3b-9c4f-e54594fcfd29', 1, 'Методы', 'Какой тип данных возвращает readLine()?

A) - String
B) - char
C) - byte[]
D) - int', '', '', 'A', 'И это правильный ответ!', 'Увы, но это не так. Вот ссылка для информации https://javarush.com/groups/posts/1381-metodih-v-java');
INSERT INTO question16 VALUES ('857ecf33-85f0-4de8-b525-57c9ecb49564', 1, 'Методы', 'Что будет выведено в консоль в результате выполнения следующего кода:

int []a = {1,2,3,4,5,6};
int i = a.length - 1;
while(i>=0){
    System.out.print(a[i]);
i--;
}


A) - 654321
B) - 123456
C) - 65432
D) - 12345', '', '', 'A', 'И это правильный ответ!', 'Увы, но это не так. Вот ссылка для информации https://javarush.com/groups/posts/1381-metodih-v-java');
INSERT INTO question16 VALUES ('e1e0b096-d2bc-4825-b9e0-b3509a8fbae9', 1, 'Методы', 'Что будет выведено в консоль в результате выполнения следующего кода:

class Ex6{
    public static void main(String args[]){
         int x = 0, y=10;
        try{
            y /=x;
        }
        System.out.print("/ by 0");
        catch(Exception e){
            System.out.print("error");
        }
    }
}


A) - Ничего, т.к. роизойдет ошибка компиляции
B) - 0
C) - error
D) - Ничего не будет выведено', '', '', 'A', 'И это правильный ответ!', 'Увы, но это не так. Вот ссылка для информации https://javarush.com/groups/posts/1381-metodih-v-java');
INSERT INTO question16 VALUES ('7225e32d-887e-4384-b644-7a86b8d20d04', 1, 'Методы', 'Что произойдет в результате выполнения данного фрагмента:

<font color="blue">class</font> Engine { }
<font color="blue">public class</font> App {
 <font color="blue">public static void</font> main(String[] args) {
     Engine e = <font color="blue">new</font> Engine();
     Engine e1 = e;
     e = <font color="blue">null</font>;
 }
}


A) - Создастся объект, не подлежащий удалению сборщиком мусора.
B) - Создастся объект, подлежащий удалению сборщиком мусора.
C) - Создадутся два объекта. Объект е подлежит удалению сборщиком мусора.
D) - Создадутся два объекта, не подлежащие удалению сборщиком мусора.', '', '', 'A', 'И это правильный ответ!', 'Увы, но это не так. Вот ссылка для информации https://javarush.com/groups/posts/1381-metodih-v-java');
