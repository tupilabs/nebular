#!/usr/bin/env perl

use strict;
use warnings;

my $countArguments;

$countArguments = $#ARGV + 1;
if($countArguments != 1) {
	print "\nUsage: parse_mf.pl <input_file_name>\n";
	exit;
}

my $fileName = $ARGV[0];

open SIGMF, "<$fileName";

my $line;
my $i; #index of expected array
my $doubleIndex; #index of doubles
my $doubleIndexFormatted;
my $value; 

$i = 0;
$doubleIndex = 0.0;
while(<SIGMF>) {
	$line = $_; 
	chomp($line);
	if($line =~ m/\s?(\d.\d{4})/) {
		$value = $line;
		$value =~ s/\s?(\d\.\d{4})/$1/;
		$doubleIndexFormatted = sprintf("%.4f", $doubleIndex);
		$line =~ s/\s?(\d\.\d{4})/expected[$i] = new double[]{$doubleIndexFormatted, $1};/;
        print $line, "\n";
        $i+=1;
        $doubleIndex+=0.1;
	} # else ignore
}