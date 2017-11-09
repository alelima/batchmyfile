# batchmyfile
A library to assist in batch processing of positional files.

# The problem

In many Systems the integration way used is exchange of positional files. These files have a layout with descripts de size and type of informations fields.
Like This:  

File content:  
0201709120001  
10012017052300129  
10022017052300130  
20002  

Layout:  
<b>Header</b> - part file description value = 0

|Position|Name|Data Type|Obligatory|
|---|---|---|---|
| 1 - 1 | part file description | Alphanumeric  | yes  |
| 2 - 9 | date of creation  | Date | yes  |
| 10 - 13  | Sequential Number  | Numeric | yes  |

<b>Detail</b> - part file description value = 1

|Position|Name|Data Type|Obligatory|
|---|---|---|---|
| 1 - 1 | part file description | Alphanumeric  | yes |
| 2 - 5 | sequential number line | Numeric | yes |
| 6 - 13  | date of payment | Date | yes |
| 14 - 20  | value of payment | Currency | yes |

<b>Trailler</b> -  part file description value = 2 , part file description; position 1 to 4, number of lines in this file.  

|Position|Name|Data Type|Obligatory|
|---|---|---|---|
| 1 - 1 | part file description | Alphanumeric  | yes  |
| 2 - 5 | number of lines in this file | Numeric | yes  |

# The Solution:

First you only have to create a class that implements the batchmyfile Layout interface, and define in this classes the same fields that are specified in the file layout documentation.
Second you have to create a object where the file values where the values will be loaded guided by some annotations.

