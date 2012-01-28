#!/usr/bin/env perl

use strict;
use warnings;

use feature 'say';

sub trim($);

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
    if($line =~ m/\s?\d.\d{4}/) {
		$value = $line;
		$value =~ s/\s?(\d\.\d{4})/$1/;
		$value = trim($value);
		$doubleIndexFormatted = sprintf("%.4f", $doubleIndex);
		$line = "expected[$i] = new double[]{$doubleIndexFormatted, $value};";
        say $line;
        $i+=1;
        $doubleIndex+=0.1;
	} elsif($line =~ m/\s?\d+\s?$/) {
		$value = $line;
        $value =~ s/\s?(\d+)\s?$/$1/;
        $value = sprintf("%.4f", $value);
        $doubleIndexFormatted = sprintf("%.4f", $doubleIndex);
        $line = "expected[$i] = new double[]{$doubleIndexFormatted, $value};";
        say $line;
        $i+=1;
        $doubleIndex+=0.1;
	}
}

sub trim($) {
	my $string = shift;
	$string =~ s/^\s+//;
	$string =~ s/\s+$//;
	return $string;
}