# Nebular

Nebular is a pure Java fuzzy logic API. With nebular you can include fuzzy logic and 
fuzzy sets to your Java code, without the need of external or binary files. 

This is possible in nebular because it uses functional programming. Instead of 
an external file defining a rule or function, you use Java objects and call 
methods passing function, predicate or other kinds of functors to model a 
fuzzy system.

## 1 minute example

The following code:

    double a = 2.0;
    double c = 4.0;
    SigmoidalMembershipFunction mf = new SigmoidalMembershipFunction(a, c);
    for(double i : new DoubleRange(0.0, 10.0, 0.1).toCollection()) {
        System.out.printf("%.4f ", mf.apply(i));
    }

Produces the following output:

    0.0003 0.0004 0.0005 0.0006 0.0007 0.0009 0.0011 0.0014 0.0017 0.0020 0.0025 0.0030 0.0037 0.0045 0.0055 0.0067 0.0082 0.0100 0.0121 0.0148 0.0180 0.0219 0.0266 0.0323 0.0392 0.0474 0.0573 0.0691 0.0832 0.0998 0.1192 0.1419 0.1680 0.1978 0.2315 0.2689 0.3100 0.3543 0.4013 0.4502 0.5000 0.5498 0.5987 0.6457 0.6900 0.7311 0.7685 0.8022 0.8320 0.8581 0.8808 0.9002 0.9168 0.9309 0.9427 0.9526 0.9608 0.9677 0.9734 0.9781 0.9820 0.9852 0.9879 0.9900 0.9918 0.9933 0.9945 0.9955 0.9963 0.9970 0.9975 0.9980 0.9983 0.9986 0.9989 0.9991 0.9993 0.9994 0.9995 0.9996 0.9997 0.9997 0.9998 0.9998 0.9998 0.9999 0.9999 0.9999 0.9999 0.9999 1.0000 1.0000 1.0000 1.0000 1.0000 1.0000 1.0000 1.0000 1.0000 1.0000 1.0000 

## Build Status

[![Build Status](https://buildhive.cloudbees.com/job/tupilabs/job/nebular/badge/icon)](https://buildhive.cloudbees.com/job/tupilabs/job/nebular/)

## Licensing

See LICENSE.txt or pom.xml for licensing.