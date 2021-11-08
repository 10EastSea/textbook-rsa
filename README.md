# Textbook RSA

1. Generate key
2. Encrypt plain text
3. Decrypt cipher text

> This program provides the values in { .. }

<br />

## 1. Generate key

This program provides public key and secret key. Public key consists of `N` and `e`. Secret key consists of `N` and `d`. `N` consists of a product of primes `p` and `q`. `e` must be 3, and `d` is the relative prime with `e` in the group of the Euler function `phi(N)`.

    pk: ({N}, {e})
    sk: ({N}, {d})

<br />

## 2. Encrypt plain text

If you give a public key `(N, e)` and `plain text`, encrypt them and give cipher text.

    {cipher text}

- `N` :  A part of the public key (Number to create a range of Z group)
- `e` :  A part of the public key
- `plain text`: plain text (Must be a number)

<br />

## 3. Decrypt cipher text

If you give a secret key `(N, d)` and `cipher text`, decrypt them and give plain text.

    {plain text}

- `N` :  A part of the public key (Number to create a range of Z group)
- `d` :  A part of the public key
- `cipher text`: cipher text (Must be a number)

<br />

# Usage

```Bash
# java version: openjdk 11.0.11
cd bin
java -cp .:../lib/bignum-projects.jar App [FLAG]
```

### Kind of FLAG
- `-g` : Generate key
- `-e <N> <e> <plain text>`  : Encrypt plain text
- `-d <N> <d> <cipher text>` : Decrypt cipher text
- `-g-hex` : Generate key (hex version)
- `-e-hex <N(hex)> <e(hex)> <plain text(hex)>`  : Encrypt plain text (hex version)
- `-d-hex <N(hex)> <d(hex)> <cipher text(hex)>` : Decrypt cipher text (hex version)
- `-help` : How to use it

### Example
```Bash
# The number was too long, so I omitted it..

# Generate key
>> java -cp .:../lib/bignum-projects.jar App -g
pk: (2079036062472838397119809930713802731.., 3)
sk: (2079036062472838397119809930713802731.., 1386024041648558931413206620475868487..)

# Encrypt plain text
>> java -cp .:../lib/bignum-projects.jar App -e 2079036062472838397119809930713802731.. 3 980522
942696787645196648

# Decrypt cipher text
>> java -cp .:../lib/bignum-projects.jar App -e 2079036062472838397119809930713802731.. 1386024041648558931413206620475868487.. 942696787645196648
980522


# Generate key (hex version)
>> java -cp .:../lib/bignum-projects.jar App -g-hex
pk: (a3489e9024f80dde35f59f5cc72e8711d19cb.., 3)
sk: (a3489e9024f80dde35f59f5cc72e8711d19cb.., 6cdb146018a55e9423f914e884c9af6136687..)

# Encrypt plain text (hex version)
>> java -cp .:../lib/bignum-projects.jar App -e a3489e9024f80dde35f59f5cc72e8711d19cb.. 3 980522
359b6de46e325a5588

# Decrypt cipher text (hex version)
>> java -cp .:../lib/bignum-projects.jar App -e a3489e9024f80dde35f59f5cc72e8711d19cb.. 6cdb146018a55e9423f914e884c9af6136687.. 359b6de46e325a5588
980522
```
