(*We have been motivated motivated by coding style present in Professor Ritwik Banerjee's slides and https://ocaml.org/learn*)

(*Problem 1*)
let rec pow a b =
  if b = 0
  then 1
  else if (b mod 2 = 0)
  then  ((pow a (b/2))*(pow a (b/2)))
  else
    (a * (pow a (b/2)) * (pow a (b/2)));;	


let rec float_pow a b =
  if b = 0
  then 1.0
  else if (b mod 2 = 0)
  then  (( float_pow a (b/2)) *. ( float_pow a (b/2)))
  else
    (a *. (float_pow a (b/2)) *. (float_pow a (b/2)));;


(*Problem 2*)

let rec compress = function
  [] -> [] 
| [a] -> [a]
| a::b::tl -> if (a = b) then compress (b::tl)
              else a::compress (b::tl);;   


(*Problem 3*)
let rec remove_if lst f =
  match lst with
     [] -> []
  | hd::tl -> if ((f hd)) then remove_if tl f
              else hd::remove_if tl f;;


(*Problem 4*)

let rec length = function
  [] -> 0
| hd::tl -> 1 + length tl;;

let rec range lst a b c =
  match lst with
    [] -> []
  | hd::tl -> if ((c >= a) && (c <= b)) then hd::range tl a b (c+1)
              else range tl a b (c+1);;       

let slice lst i j =
  let len = length lst in
    let clean lenlist lst a b =
      if (a > b || a > lenlist || lenlist = 0 ) then []
      else if (b > lenlist) then range lst a (lenlist-1) 0
      else range lst a (b - 1) 0
    in clean len lst i j;;

(*Problem 5*)

let rec rev = function
  [] -> []
| hd::tl -> (rev tl) @ [hd];;

let check_and_insert element func = function
  [] -> false
| hd::tl -> func element hd;;

let rec place element func = function
  [] -> [[element]]
| hd::tl -> if (check_and_insert element func hd) then  (element::hd)::tl
            else hd::(place element func tl) ;; 

let rec equivs_rev func = function
  [] -> []
| hd::tl -> place hd func (equivs_rev func tl);;  

let equivs func lst = rev (equivs_rev func lst);;

(*Problem 6*)

let isPrime n = 
  let rec primechecker n i =
    if ( n = 1 ) then false
    else if ( i = 1 ) then true
    else if ( (n mod i) = 0) then false
    else (primechecker n ( i  - 1))
  in primechecker n ( n - 1)
 
let goldbachpair n =
  if ( (n mod 2) = 1 || n=2) then (0,0)
  else let rec recurr a b =
    if ((isPrime a ) && (isPrime b ) ) then (a,b)
    else recurr (a + 1) (b - 1)
  in recurr (2) (n - 2);; 

(*Problem 7*)

let rec equiv_on f g = function
  [] -> true
| hd::tail -> if ( ( f hd ) = ( g hd ) ) then equiv_on f g tail 
              else false;;

(*Problem 8*)

let rec pairwisefilter cmp = function
  [] -> []
| [a] -> [a]
| a::b::tail -> if ( ( cmp a b) = a ) then a::pairwisefilter cmp tail
                else b::pairwisefilter cmp tail;;

(*Problem 9*)

let rec internal_poly lst x =
  match lst with
  [] -> 0
| hd::tl -> ( let (a , b) = hd in a * (pow x b) ) + internal_poly tl x;;

let polynomial lst = internal_poly lst ;;

(*Problem 10*)
(*The bitset based algorithm was partially inspired from algorithm here https://www.geeksforgeeks.org/power-set/. The sorting of those bitsets to get size in order and OCAML code is purely my own*)


let rec ones = function
  0 -> 0
| i -> (i mod 2) + ones (i/2);; 

let rec bit_to_list a = function
  [] -> []
| hd::tl -> if ((a mod 2) = 1) then hd::(bit_to_list (a/2) tl) 
            else bit_to_list (a/2) tl;;  

let rec make_set total a n lst =
  if (a = (total+1)) then []
  else if ( (ones a) = n) then bit_to_list a lst :: make_set total (a + 1) n lst
  else make_set total (a+1) n lst;;  
   

let make_power_set_n n len lst =
  make_set ((pow 2 len)-1) 0 n lst;; 

let rec make_powerset n len lst =
   if (n = -1) then []
   else (make_powerset (n-1) len lst) @ (make_power_set_n n len lst);; 

let powerset lst = let len = length lst in make_powerset len len lst;;
   
