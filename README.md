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
<b>Header</b> - position 0 (value = 0), part file description; position 1 to 8, date of creation; position 9 to 12, sequential number.  
<b>Detail</b> - position 0 (value = 1), part file description; position 1 to 3, sequential number line; position 4 to 11, date of payment; position 12 to 16, value of payment.  
<b>Trailler</b> - position 0 (value = 2), part file description; position 1 to 4, number of lines in this file.  

In construction...
