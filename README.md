/************************************************/
Samu Java átirat 
készítette: Szimeonov Nikolett
/************************************************/


Először is ezt a két parancsot add ki abban a mappában ahova letöltötted a link-grammart:
(De ha már létezne a liblink-grammar.so.5 és a liblink-grammar-java.so akkor töröld ki őket és add ki újra a parancsot) 

sudo ln -sv "$(pwd)/link-grammar/.libs/liblink-grammar.so.5" /usr/lib/

sudo ln -sv "$(pwd)/bindings/java-jni/.libs/liblink-grammar-java.so" /usr/lib/


Ezek után fordítás:
javac -cp /home/bszabo/link-grammar-5.2.5/bindings/java/linkgrammar-5.2.5.jar *.java

(értelemszerűen a cp után a saját link grammar mappádat írd, és azon belül a bindings/java/linkgrammar-vmennyi.jar-t)

Majd a futtatás: 

java -cp /home/bszabo/link-grammar-5.2.5/bindings/java/linkgrammar-5.2.5.jar:`pwd` Main

(itt megint változhat nálad a link-grammar, ha megcsináltad a szimbolikus linkeket a :`pwd` ne maradjon le a végéről)


