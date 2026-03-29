import os
import re

css_dir = r"c:/Dev_antigravity_git/meta_system/nproject/src/main/resources/static/css"
html_dir = r"c:/Dev_antigravity_git/meta_system/nproject/src/main/resources/templates"

# Regex patterns for flat design
patterns = [
    # 1. border-radius -> 0
    (r"border-radius:\s*[^;{]+;", r"border-radius: 0;"),
    
    # 2. box-shadow -> none
    (r"box-shadow:\s*[^;{]+;", r"box-shadow: none;"),
    
    # 3. transition -> none
    (r"transition:\s*[^;{]+;", r"transition: none;"),
    
    # 4. Remove :hover blocks
    # We use (?s) for dotall so .*? matches newlines, and match non-greedy .*? till the closing brace
    # and [^}{]* to not match other rules' braces.
    (r"(?is)[^{\n}]*?:hover\s*\{[^{}]*\}", r""),
    
    # 5. Color replacements
    (r"#00bcd4", r"#2f6fed"),
    (r"#00acc1", r"#2f6fed"),
    (r"#1976d2", r"#2f6fed"),
    (r"#ff5252", r"#2f6fed"),
    (r"#ff4500", r"#2f6fed"),
    (r"#0097ff", r"#4da3ff"),
    (r"#0066fa", r"#2f6fed"),
    (r"#f4f6f8", r"#f5f7fa"),
    (r"#f5f5f5", r"#f5f7fa"),
    (r"#f9f9f9", r"#f5f7fa"),
    (r"#f0f4f8", r"#f5f7fa"),
    (r"#e3f2fd", r"#ffffff"),
    (r"#e0e0e0", r"#dcdcdc"),
    (r"#ccc;", r"#dcdcdc;"),
    (r"#ccc\s", r"#dcdcdc "),
    (r"#eee;", r"#dcdcdc;"),
    (r"#eee\s", r"#dcdcdc "),
    (r"#333;", r"#333333;"),
    (r"#333\s", r"#333333 "),
    (r"#555;", r"#666666;"),
    (r"#555\s", r"#666666 "),
    (r"#666;", r"#666666;"),
    (r"#666\s", r"#666666 ")
]

def process_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    
    original = content
    for pattern, repl in patterns:
        content = re.sub(pattern, repl, content)
    
    if content != original:
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(content)
        print(f"Updated: {filepath}")

def process_dir(directory, ext):
    for root, dirs, files in os.walk(directory):
        for file in files:
            if file.endswith(ext):
                process_file(os.path.join(root, file))

if __name__ == "__main__":
    process_dir(css_dir, ".css")
    process_dir(html_dir, ".html")
    print("Done applying design concepts.")
