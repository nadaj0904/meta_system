import os
import sys
import subprocess

try:
    from PIL import Image, ImageOps
except ImportError:
    print("Installing Pillow...")
    subprocess.check_call([sys.executable, "-m", "pip", "install", "Pillow"])
    from PIL import Image, ImageOps

def create_thumbnails():
    base_dir = r"C:\Dev_antigravity_git\meta_system\nproject\src\main\resources\static\img\product\kyobo"
    
    folders_to_process = ["methods", "summary", "terms"]
    
    target_size = (480, 270)
    
    for folder in folders_to_process:
        source_dir = os.path.join(base_dir, folder)
        target_dir = os.path.join(base_dir, folder + "_thum_270")
        
        if not os.path.exists(source_dir):
            print(f"Source folder not found: {source_dir}")
            continue
            
        if not os.path.exists(target_dir):
            os.makedirs(target_dir)
            print(f"Created target folder: {target_dir}")
            
        for filename in os.listdir(source_dir):
            if filename.lower().endswith(('.png', '.jpg', '.jpeg', '.gif', '.bmp')):
                source_path = os.path.join(source_dir, filename)
                target_path = os.path.join(target_dir, filename)
                
                try:
                    with Image.open(source_path) as img:
                        # Resize the image using center crop while maintaining aspect ratio
                        thumb = ImageOps.fit(img, target_size, method=Image.Resampling.LANCZOS, centering=(0.5, 0.5))

                        # Save maintaining format
                        thumb.save(target_path)
                        print(f"Created thumbnail for: {filename} in {folder}_thum_270")
                except Exception as e:
                    print(f"Error processing {filename}: {e}")

if __name__ == "__main__":
    create_thumbnails()
