
import tkinter as tk
from tkinter import filedialog, messagebox
import subprocess
import os

def select_image():
    """
    Open a file dialog to select an image.
    """
    file_path = filedialog.askopenfilename(filetypes=[("Image Files", "*.png;*.jpg;*.jpeg")])
    if file_path:
        image_path.set(file_path)
        label_image.config(text=f"Selected: {file_path}")

def apply_effect(effect):
    """
    Apply the selected effect using the Java program.
    """
    input_file = image_path.get()
    if not input_file:
        messagebox.showerror("Error", "Please select an image first.")
        return

    output_file = os.path.join(os.path.dirname(input_file), f"edited_{os.path.basename(input_file)}")
    try:
        subprocess.run(["java", "PhotoEditor", input_file, effect, output_file], check=True)
        messagebox.showinfo("Success", f"Effect applied! Saved to {output_file}")
    except subprocess.CalledProcessError as e:
        messagebox.showerror("Error", f"Failed to apply effect: {e}")

# Create the GUI
app = tk.Tk()
app.title("Photo Editor")

image_path = tk.StringVar()

frame = tk.Frame(app)
frame.pack(pady=10)

label_image = tk.Label(frame, text="No image selected.")
label_image.pack(pady=5)

btn_select = tk.Button(frame, text="Select Image", command=select_image)
btn_select.pack(pady=5)

btn_grayscale = tk.Button(frame, text="Apply Grayscale", command=lambda: apply_effect("grayscale"))
btn_grayscale.pack(pady=5)

btn_sepia = tk.Button(frame, text="Apply Sepia", command=lambda: apply_effect("sepia"))
btn_sepia.pack(pady=5)

app.mainloop()
