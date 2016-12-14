#!/usr/bin/awk -f

BEGIN {
   srand();
   for (i = 0; i < 1000; ++i) {
     printf "/summer?n=%d\n", int(1000000 + rand() * 1000);
   }
}
