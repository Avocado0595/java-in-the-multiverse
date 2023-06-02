
### 1. Variable Arguments
```java
public void add(int ...arr){ 
        int sum=0;
       for(int i=0; i<arr.length;i++){
           sum+=arr[i];
       }
       System.out.print(sum);
    }
add(1,2,3,4,5); //15
/*
arr nhận 1,2,3,4,5 thành mảng: [1,2,3,4,5]
*/
```
