use std::env;
use std::fs::File;
use std::io::{self, BufRead};
use std::path::Path;

fn main() {
    let args: Vec<String> = env::args().collect();

    if args.len() > 1 {
        let txt_file = &args[1];
        println!("The txt file provided is: {}", txt_file);

        match read_and_process_file(txt_file) {
            Ok(sum) => println!("Sum of concatenated numbers: {}", sum),
            Err(e) => println!("Error: {}", e),
        }
    } else {
        println!("No txt file provided");
    }
}

fn read_and_process_file(filename: &str) -> Result<i64, io::Error> {
    let path = Path::new(filename);
    let file = File::open(&path)?;
    let reader = io::BufReader::new(file);

    let mut sum = 0;
    for line in reader.lines() {
        let line = line?;
        let (first, last) = find_first_last_numeric(&line);
        if let (Some(first_num), Some(last_num)) = (first, last) {
            let concatenated = format!("{}{}", first_num, last_num).parse::<i64>().unwrap_or(0);
            sum += concatenated;
        }
    }

    Ok(sum)
}

fn find_first_last_numeric(line: &str) -> (Option<char>, Option<char>) {
    let digits: Vec<char> = line.chars().filter(|c| c.is_digit(10)).collect();
    if !digits.is_empty() {
        (digits.first().cloned(), digits.last().cloned())
    } else {
        (None, None)
    }
}
