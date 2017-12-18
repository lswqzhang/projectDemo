BEGIN{FS=" "}
'BEGIN{print n}
{
    if (n == 1) print $0
    if (n == 2) print $1
}' n = 1 test
